public class NBody {
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int planetsNum = in.readInt();
		double radius = in.readDouble();
		in.close();

		return radius;
	}

	public static Planet[] readPlanets(String fileName) {
		/* Start reading in fileName.txt */
		In in = new In(fileName);
		int planetsNum = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[planetsNum];
		/* Keep looking until the file is empty. */
		int i = 0;
		while(i < planetsNum) {
			planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
			i++;
		}
		return planets;
	}
	public static void main(String[] args){
		double T = Double.valueOf(args[0].toString());
		double dt = Double.valueOf(args[1].toString());
		String filename = args[2];
		Planet[] planets = readPlanets(filename);
		double radius = readRadius(filename);
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0 ,0 ,"images/starfield.jpg");
		for(Planet p : planets){
			p.draw();
		}
		StdDraw.show();
		StdDraw.enableDoubleBuffering();
		double time = 0;
		while (time < T){
		   double[] xforce = new double[planets.length];
		   double[] yforce = new double[planets.length];
		   for(int i = 0;i < planets.length;i++){
			  xforce[i] = planets[i].calcNetForceExertedByX(planets);
			  yforce[i] = planets[i].calcNetForceExertedByY(planets);
		   }
		   for(int i = 0;i < planets.length;i++){
			  planets[i].update(dt, xforce[i], yforce[i]);
		   }
		   StdDraw.picture(0 ,0 ,"images/starfield.jpg");
		   for(Planet p: planets){
			  p.draw();
		   }
		   StdDraw.show();
		   StdDraw.pause(10);
		}
		StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
	}
}