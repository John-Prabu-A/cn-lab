# Create a new NS Simulator instance
set ns [new Simulator]

# Open a trace file for NAM (Network Animator)
set namfile [open out.nam w]
$ns namtrace-all $namfile

# Define nodes
set n0 [$ns node] ;# Source Node 0
set n1 [$ns node] ;# Source Node 1
set n2 [$ns node] ;# Router Node
set n3 [$ns node] ;# Destination Node

# Orientation and color labeling
$n0 color blue
$n1 color green
$n2 color orange
$n3 color red

# Create duplex links with specified bandwidth and delay
$ns duplex-link $n0 $n2 10Mb 10ms DropTail
$ns duplex-link $n1 $n2 10Kb 100ms DropTail
$ns duplex-link $n2 $n3 10Kb 100ms DropTail

# Attach TCP agent to n0 and TCP Sink agent to n3
set tcp [new Agent/TCP]
$tcp set packetSize_ 200 ;# Set packet size
$tcp set interval_ 0.01 ;# Time interval between packets
$ns attach-agent $n0 $tcp

set tcpSink [new Agent/TCPSink]
$ns attach-agent $n3 $tcpSink
$ns connect $tcp $tcpSink

# Attach UDP agent to n1 and NULL agent to n3
set udp [new Agent/UDP]
$udp set packetSize_ 300 ;# Set packet size
$udp set interval_ 0.001 ;# Time interval between packets
$ns attach-agent $n1 $udp

set null [new Agent/Null]
$ns attach-agent $n3 $null
$ns connect $udp $null

# Attach FTP traffic to TCP agent
set ftp [new Application/FTP]
$ftp attach-agent $tcp
$ftp set type_ FTP
$ns at 0.3 "$ftp start"

# Attach CBR traffic to UDP agent
set cbr [new Application/Traffic/CBR]
$cbr attach-agent $udp
$cbr set packetSize_ 300
$cbr set interval_ 0.001 ;# Same as UDP interval
$ns at 0.1 "$cbr start"

# Stop traffic generators at 5 seconds
$ns at 5.0 "$ftp stop"
$ns at 5.0 "$cbr stop"

# Schedule simulation end and NAM file closure
$ns at 5.1 "finish"

proc finish {} {
    global ns namfile
    $ns flush-trace
    close $namfile
    exec nam out.nam &
    exit 0
}

# Run the simulation
$ns run
