# 2. Gebruik docker

Date: 2022-09-11

## Status

Accepted

Required by [3. Haproxy als loadbalancer](0003-haproxy-als-loadbalancer.md)

Required by [4. Gebruik Postgres](0004-gebruik-postgres.md)

## Context

Voor demos is het essentieel dat je gewoon kan 'git clonen en gaan'. Dus geen geneuzel met config-filetjes of wat dan ook.

## Decision

We gebruiken Docker met docker-compose voor het maken van de applicatie-images.

## Consequences

Met Docker-compose is er een standaard commando (docker-compose up) waarmee studenten alle ondersteunende infrastructuur kunnen zien werken, zonder grote aanpassingen aan hun eigen systeem te doen.

## Rejected Alternatives

Het is ook mogelijk om fancy IntelliJ run-configuraties aan te leveren. Alleen is het lastig die dingen goed in te checken, zodra je eenmaal de .idea folder aan git toevoegt krijgt elke student met andere plugins geïnstalleerd net een subtiel andere ervaring.
