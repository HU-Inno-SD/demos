# GRPC hello world

Klein hello-world voorbeeldje van GRPC.

Hoe draai je het?
Op de commandline kun je gewoon maven compile draaien. Dit gaat de eerste keer in IntelliJ waarschijnlijk mis.
Dit project gebruikt namelijk gegenereerde code, en dat vindt IntellIJ een beetje lastig.
Om dat aan te geven moet je eenmalig rechtklikken op elk project (client & server) en Maven -> Generate Sources and Update directories aanklikken.

TODO:
Ik heb de .proto file nu gekopieerd in elk project. Netter is om met de <protoSource> parameter aan de plugin 1 plek te definieren,
dit ging alleen niet zo soepel als gehoopt... Dus dat is nog een belangrijk verbeterpuntje:)
