# Debuggen

1. `docker pull ubuntu #men neme een container`
2. `docker run -it --name nat-demo ubuntu /bin/bash #en log er eens op in`
	a. (je kunt er meerdere terminals op openen met `docker exec -it nat-demo /bin/bash`
	b. we hebben (een subset van) tcpdump, iproute2, net-tools en iputils-ping nodig.
3.  Op de container kun je runnen: `tcpdump -i eth0 -s 1024 -w /root/trace.pcap`
4. Op de host: `docker exec nat-demo tail -n +0 -f /root/trace.pcap | "C:\Program Files\Wireshark\Wireshark.exe" -k -i -`