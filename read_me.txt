**********************************************
	Example Genetic Algorithm (GA) code
	by Richard Smith
**********************************************

This code is written to provide an example of my code.
It is a framework for writing GAs, so that using a
GA on a new problem can be implemented using a modest
amount of new code. 

Shows the way GAs solve a problem as well as some of the
characteristics of GAs, e.g. quick to find a good solution
but will sometime take time to find a great solution.
On the Travelling Salesman problem this is fine as finding
on optimal solution for a large number of points is 
computationally intractable, but finding a good solution
quickly may be all that is required. 

******************
Class-by-class
******************
GeneticAlgorithm.java:
The main method in GeneticAlgorithm.java. For a new
application the you'll need to modify the main method, 
the other methods will be largely unchanged. 

GAView.java:
Simple view for the application, requires no 
changes for a new application unless you want a different
size of window. 

Population.java:
Simple class to hold a set of population individuals

PopulationFactory.java:
Class to create an individual for the population. This 
is extended for each new problem. 

PopulationItem.java:
These are the individuals in the population. Each problem
will require the programmer to extend this class. 

PopulationRenderer.java:
Renders the population on screen for the user to follow
progress. This class needs an implementation for 
each new problem. 

Breeder.java:
Used to breed the population. I'd like to make this more
configurable rather than have the config in object variables.

Evaluator.java:
Has the population items evaluate themselves, then sorts
the population based on their cost. Should not require 
re-coding for a new problem. 

TravellingSalesmanPopulationFactory.java:
Implements the PopulationFactory

TravellingSalesmanPopulationItem.java:
Domain specific implementation of the PopulationItem class. 

TravellingSalesmanPopulationRenderer.java
Renders the population to the screen. For this problem it
shows the paths of the population in green and the path
of the best in population in red. It also shows the 
generation and the current best score. 

Locations.java:
Domain specific class to hold the locations of the points
to be visited in the problem. Can generate either a random
set of points or points in a circle - useful for testing. 

******************
Other notes:
The speed of the evolution is throttled so that the user can 
see the path evolving. Could make the throttle dynamic, e.g. 
based on whether the GA has improved the score from one
generation to the next. 

The GAView doesn't handle resize - this a KIS choice. 

There might better way to go with the configuration of the 
locations class (See the main method in GeneticAlogrithms).
This is quite a general solution but maybe overkill for such
a simple class.
 
Code can work with either fitness or cost without too much
re-coding.

I'd like to make the mutation configurable or dynamic, at 
the moment it is hard coded. 

******************
History:
6th June 2011 -Initial version, quickly written. 
Feb 2012 - Review and modest updates

