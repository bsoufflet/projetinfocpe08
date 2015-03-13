# Introduction #

Subclipse est un plugin pour eclipse qui permet d'utiliser les avantages de SVN.


# Details #

  * Tout d'abord vous devez avoir [eclipse](eclipse.md)
  * Installer le plugin subclipse (Aptana dispose peut etre deja de ce plugin...)
> Pour cela aller voir ici : http://subclipse.tigris.org/install.html et utilisez la version 1.4.
  * Ouvrez la perspective Subclipse Repository "SVN repository Exploring"
  * Dans le panneau de gauche faites un click droit et selectionnez "new repository"
> L'URL est https://projetinfocpe08.googlecode.com/svn et vous trouverez votre password ici http://code.google.com/hosting/settings lorsque vous êtes logger sur google code.
  * cliquez droit sur le nouveau repository ajoute et faites checkout en temps que nouveau projet.
  * sélectionnez le type de projet (se sera surement un projet php) donnez lui un nom et lancez!
  * dans la perspective de votre projet (dans mon cas php) vous obtenez maintenant un nouveau projet.

pour utiliser subclipse allez voir [subclipseUse](subclipseUse.md)


NB: I've seen situations where subclipse (or subversion) would keep asking for a password; I managed to stop it from doing this by going into the Eclipse > Window > Preferences, expanding the Team folder, selecting SVN and ensuring that the SVN interface was selection was set to SVNKit (Pure Java).