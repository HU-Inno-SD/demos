# 4. Gebruik Postgres

Date: 2022-09-11

## Status

Accepted

Requires [2. Gebruik docker](0002-gebruik-docker.md), want als we niet iets als Docker gebruiken moet iedereen die de code uitcheckt 'eerst even pielen met de database settings'

## Context

We willen laten zien dat je welliswaar het counter-probleem met een database zou kunnen oplossen, maar dat je dan nog steeds een consistency probleem hebt met in-memory-caching.

## Decision

Voor de shop-database gebruiken we Postgres als echte database

## Consequences

Het opstarten is nu een beetje rommelig, want beide containers willen de database create-droppen... Dat is niet zo handig. Daarom is de compose-file nu wat ingewikkelder dan zo moeten, en dat lekt ook in de opstartlogica middels extra Environment-variables.

## Rejected Alternatives

Gewoon in-memory H2 blijven gebruiken, dat maakt het verschil met de counter-demo te klein. Dan zie je niet dat 'gewoon een database toevoegen' niet genoeg is.

