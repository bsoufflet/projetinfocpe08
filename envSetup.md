# JAVA #

JDK 6 Update 11

http://java.sun.com/javase/downloads/index.jsp


# Eclipse #

http://www.eclipse.org/downloads/

Download and extract Eclipse IDE for Java EE Developers (163 MB)



# JBOSS Server #

Download and extract http://downloads.sourceforge.net/jboss/jboss-5.0.0.GA-jdk6.zip?modtime=1228452481&big_mirror=1



# JBOSS Tools pour Eclipse #

http://www.jboss.org/community/docs/DOC-10044



dans JBoss tools selectioner: JBoss AS Tools , (Struts tools) peut etre pas car que pour struts1, RichFaces

une fois installe, on peut setup le server dans eclipse : dans la view server add>new server> JBoss AS 5.0 puis indiquer le path du JBoss. Pour la configuration du runtime prenez « default »





# Subclipse (SVN pour google code) #

http://subclipse.tigris.org/install.html



puis voir le wiki ici [subclipse](subclipse.md)


# Mise en place du projet #
g crée un projet web a partir de stuts2-blanc que g commit. ya just a le checkout…


pour le checkout:
une fois que vous avez ajoute le repository de google code dans le plugin SVN, vous faite nouveau projet>checkout project from SVN repository.
Il y a 2 projets a checkout séparément, WebDomotic et EJBDomotic.



dessus ya des example de JSP deja faite. Assurez vs que votre server JBoss est en marche avant d'essayer de visualiser les pages web.


je pense que ca sera plus simple si benjamin vous montre comment utiliser svn.

y’aura surement besoin d’un ou plusieurs EJB pour faire des sessions-beans, pour différencier les users.



# DB setup for JBoss #

http://docs.jboss.org/jbossas/getting_started/v4/html/db.html


# doc externe #



http://www.laliluna.de/webprojects-eclipse-jbosside-tutorial-en.html



note : JBOSS IDE a ete remplace par JBoss tools plugin pour eclipse.



http://www.laliluna.de/first-steps-with-struts-free-tools-en.html
