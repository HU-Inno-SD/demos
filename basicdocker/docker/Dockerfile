# Use the latest Alpine Linux base image.
FROM alpine:latest

# Run a command on the container image, since we're using Linux we will use the `mkdir` command to create a new directory.
RUN mkdir /app/

# Set the working directory of the container image.
# This will be the default directory for copying files, executing commands and opening new shells.
WORKDIR /app/

# This directive is used for copying files from the host machine that builds the image into the actual image.
# Note that `.` means current directory in Linux, since we specified `/app/` as our working directory. `./init.sh` will be copied to `/app/init.sh/` on the image's filesystem.
COPY ./image/init.sh .

# Specify which command should be executed when the container starts.
ENTRYPOINT "./init.sh"
