# 3. Haproxy als loadbalancer

Date: 2022-09-11

## Status

Accepted

Requires [2. Gebruik docker](0002-gebruik-docker.md), want de demo moet werken op Windows, en Haproxy draait niet zomaar op Windows. Dus draaien als container in WSL2.

## Context

Om een simpele demo te geven van 'problemen die je krijgt als je gaat distribueren' wouden we simpele code laten zien, die het doet als je 1 instantie hebt, maar wat mis gaat zodra je meerdere instanties hebt. Om daarbij ook het principe van Distribution Transparancy te laten zien is een Reverse-Proxy noodzakelijk.

## Decision

Haproxy is de gekozen loadbalancer.

## Consequences

Haproxy is prima in staat om de demo uit te voeren en is een (ietwat ouderwetse maar nog steeds prima functionerende) loadbalancer.

## Rejected Alternatives

NGinx, want die heeft wel een Windows-versie. Het probleem was echter dat NGinx niet wou opstarten als de containers-om-naar-te-loadbalancen nog niet bestonden. Daar waren workarounds voor, maar die kreeg ik binnen de timebox niet aan de praat. In het algemeen vond ik het config-file-format van NGinx minder prettig om mee te werken, maar niet dramatisch.

Fancy-service-discovery-product-X, zoals Consul. Geen tijd gehad om te onderzoeken. Eerste indruk was dat het te complex zou worden.

(deze alternatieven zijn dus nog steeds prima opties!)