<h1> How tu run the docker container </h1>

<h2> First: BUILD </h2>
docker build -f Dockerfile -t datadome:latest .

<h2> Then: RUN </h2>
docker run -i -t --name datadomeContainer -p 8080:8080 datadome:latest

<h3>to eliminate the containers use the following command: </h3>
docker rm -f $(docker ps -aq) 

<h2> If the dockerfile does not work... </h2>
<p>  cd detectionsystem </p>
<p> javac detectionsystem/*.java </p>
<p> java detectionsystem.Main </p>

### et voilà :)



