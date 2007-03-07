rem  Uncomment one line at a time and install these 
rem  things to your Maven's repository (JARs cannot be found in 
rem  the standard locations).

mvn -U install:install-file -DgroupId=jena -DartifactId=arq-extra -Dversion=2.5.2 -Dpackaging=jar -Dfile=../edu_demo/webapp/WEB-INF/lib/arq-extra.jar
rem mvn install:install-file -DgroupId=org -DartifactId=json -Dversion=1.0 -Dpackaging=jar -Dfile=../edu_demo/webapp/WEB-INF/lib/json.jar
rem mvn install:install-file -DgroupId=javax.transaction -DartifactId=jta -Dversion=1.0.1B -Dpackaging=jar -Dfile=../edu_demo/webapp/WEB-INF/lib/jta.jar
rem mvn install:install-file -DgroupId=jena -DartifactId=jena -Dversion=2.5.2 -Dpackaging=jar -Dfile=../edu_demo/webapp/WEB-INF/lib/jena.jar
rem mvn install:install-file -DgroupId=jena -DartifactId=arq -Dversion=2.5.2 -Dpackaging=jar -Dfile=../edu_demo/webapp/WEB-INF/lib/arq.jar
rem mvn install:install-file -DgroupId=jena -DartifactId=iri -Dversion=2.5.2 -Dpackaging=jar -Dfile=../edu_demo/webapp/WEB-INF/lib/iri.jar


The plugin 'org.apache.maven.plugins:maven-install-plugin' does not exist or no valid version could be found