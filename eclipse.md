## Installation d'Eclipse ##

Prerequis : Java (JDK1.6) doit etre installé

  1. telecharger Eclipse IDE for Java EE Developers en version non executable
  1. désarchiver le fichier telecharger où vous voulez
  1. executer eclipse.exe
  1. choisir un workspace qui convienne avec JBOSS (je sais pas ou...)
  1. Installer Aptana plugin pour javascript, HTML et MYSQL (possibilite de naviguer dans la BD comme php my admin):
    * From the Help menu, select Software Updates > Find and Install... to open an Install/Update pop-up window.
    * On the Install/Update pop-up window, choose the Search for new features to install option, and click the Next button.
    * Set up a new remote site to scan for updates.
      * Click the New Remote Site button to open a New Update Site pop-up window.
      * On the New Update Site pop-up window, type the name of the new plug-in in the site Name text box.
      * In the URL text box, type the URL http://update.aptana.com/update/studio/3.2/ for the update site.
      * Click OK.
      * Click the Finish button to open an Updates window.
    * On the Updates window, check the box next to the name of the plug-in, and click the Next button.
    * Choose the option to accept the terms of the license agreement, and click the Next button.
    * Click the Finish button.
    * Click the Install All button.
  1. Installer [subclipse](subclipse.md) pour pouvoir utiliser le SVN de google code
  1. installer les autres plugin Jboss