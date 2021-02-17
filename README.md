<h1> How tu run the docker container </h1>

<h2> First: BUILD </h2>
docker build -f Dockerfile -t datadome:lastest .

<h2> Then: RUN </h2>
docker run -i -t --name datadomeContainer -p 8080:8080 datadome:latest

<h3> If the dockerfile does not work... </h3>
<div class="container">
  cd detectionsystem
  javac detectionsystem/*.java
  java detectionsystem.Main
  ´´´

</div>
et voilà :)
