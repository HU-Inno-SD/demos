# Docker

> ⚠️ READ THIS BEFORE USING DOCKER ⚠️
> Please make sure to read the warning in the parent directory before proceeding.

Basic Docker allows you to create images using Dockerfiles.
These files contain definitions on how the image should be built.  

> Note: Docker has the concept images and containers:  
Images are immutable snapshots, containers are instances of images.


See `Dockerfile` for an example image definition.  
In this example, we start from the minimalist Alpine Linux base image.  
We apply additional instructions to this base image in order to create our customized image.  
Check the other files to see how it works.

## Common commands

A one-liner to build and run the custom docker image:

```shell
docker build . -t bongo-cat && docker run bongo-cat --rm
```
If you see the text 'Yay, it worked!' accompanied by some gibberish it means that your either your terminal or the font you're using does not have UTF-8 character encoding support.
Don't worry, you won't be able to see the bongo cat, but you managed to make it work!

Note that if you are using Linux or WSL you will need to use `sudo` as Docker manipulation requires superuser privileges:
```shell
sudo docker build . -t bongo-cat && sudo docker run bongo-cat --rm
```

A breakdown of the command we just used:
- `docker build .` Tells Docker to build a new image using the `Dockerfile` in the current `.` directory
- `-t bongo-cat` This instructs Docker to tag the image we just build with `bongo-cat` so we can reference it later
- `&&` default shell chaining, only runs the second command when the previous succeeded
- `docker run bongo-cat` Start the image we built in the previous step
- `--rm` Remove the container after it has been stopped.


### Other useful commands and things to know

To check which containers are currently running: `docker ps`

Every running container has a unique ID (hash). You can show it by running the 'docker ps' command.  
This hash can also be used to further interact with a container.

To open a new shell inside the running container: `docker exec -it <id> /bin/sh`

To stop a container: `docker stop <id>`

To stop and remove a container: `docker down <id>`

Check `docker --help` or `man docker` for more info.  
It is recommended to read a bit of the Docker documentation online to get more familiar with the concepts.
