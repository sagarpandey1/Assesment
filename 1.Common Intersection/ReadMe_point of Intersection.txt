#1: Common point of intersection

The approach used here to find common point of intersection is:
	 Assuming the equation of three planes in the form :
		 a1x+b1y+c1=d1
		 a2x+b2y+c2=d2
		 a3x+b3y+c3=d3
	 User should input
	 (1) the matrix of order 3*3 made by coefficients of variables of 3 planes as:
			 a1 b1 c1
			 a2 b2 c2
			 a3 b3 c3
	 (2)matrix of order 3*1 with the constants:
			 d1
			 d2
			 d3
Then we check the determinant of coefficient matrix:
 if(determinant(coefficient matrix))!=0; then common point of intersection exists between three planes.
 	else a common point of intersection does not exist.

 To find the common point of intersection finally, we use the Cramer's rule.
