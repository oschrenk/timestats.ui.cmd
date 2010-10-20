# README #

## Installation ##


	git clone git@github.com:oschrenk/timestats.core.git
	git clone git@github.com:oschrenk/timestats.ui.cmd.git
	cd timestats.core
	mvn install
	cd ..
	cd timestats.ui.cmd
	mvn clean package
	chmod u+x timestats

## Usage ##

	./timestats path/to/cvslogs/
	./timestats path/to/cvslogs/ | grep project