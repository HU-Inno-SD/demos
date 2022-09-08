# Docker Compose

> ⚠️ READ THIS BEFORE USING DOCKER ⚠️
> Please make sure to read the warning in the parent directory before proceeding.

Docker compose is a wrapper for Docker that allows you to easily configure deployment of containers.
Additionally, it provides some abstractions for inter-container communication.

As an example, a command to run a postgres database with Docker looks as follows:
```shell
docker run -v "./somelocalvolume:/var/lib/postgresql/data" -e "POSTGRES_PASSWORD=1q2w3e!" --restart unless-stopped -p "127.0.0.1:5432:5432" postgres:latest
```

While this is nice for ad-hoc running some containers, it gets a bit tedious when configuring a whole deployment.
This is something that docker-compose solves through yaml configurations.

An equivalent `docker-compose.yml` configuration looks like:
```yaml
version: "3.9"
services:
  postgres:
    image: postgres:latest
    restart: unless-stopped
    ports:
      - "127.0.0.1:5432:5432"
    volumes:
      - ./somelocalvolume:/var/lib/postgresql/data
    environment:
      - "POSTGRES_PASSWORD=1q2w3e!"
```

You can then launch the deployment using `docker-compose up -d`.  
Note that the `-d` flag starts the containers detached from your current shell.  
If you omit it, the deployment will shut down as soon as you exit your shell.

Check the `/basicboot` directory of this repository for a practical example.

Note that the service names are also hostnames through which the containers can reach each other.
So in the `basicboot` example, `web1` can resolve the database by looking up `pg`.

Find the docker-compose specification online for more configuration options.
