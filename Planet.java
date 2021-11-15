
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p1){
        double xDistance = 0.0;
        double yDistance = 0.0;
        xDistance = this.xxPos - p1.xxPos;
        yDistance = this.yyPos - p1.yyPos;
        double squareX = Math.pow(xDistance,2);
        double squareY = Math.pow(yDistance,2);
        double xyDistance = Math.sqrt(squareX+squareY);
        return xyDistance;
    }
    public double calcForceExertedBy(Planet p2){
        double force = (G * p2.mass * this.mass) / (this.calcDistance(p2) * this.calcDistance(p2));
        return force;
    }
    public double calcForceExertedByX(Planet p3){
        double forceX = this.calcForceExertedBy(p3) * ( p3.xxPos - this.xxPos) / this.calcDistance(p3);
        return forceX;
    }
    public double calcForceExertedByY(Planet p3){
        double forceY = this.calcForceExertedBy(p3) * (p3.yyPos - this.yyPos) / this.calcDistance(p3);
        return forceY;
    }
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double netX = 0;
        for(int i = 0;i < allPlanets.length;i++){
            if(this.equals(allPlanets[i])) {
                continue;
            }
            netX += this.calcForceExertedByX(allPlanets[i]);
        }
        return netX;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netY = 0;
        for (int i = 0; i < allPlanets.length; i++) {
            if(this.equals(allPlanets[i])) {
                continue;
            }
            netY +=this.calcForceExertedByY(allPlanets[i]);
        }
        return netY;
    }
    public void update(double dt,double xForce,double yForce){
        double aNet_x = xForce / this.mass;
        double aNet_y = yForce / this.mass;
        this.xxVel = this.xxVel + (dt * aNet_x);
        this.yyVel = this.yyVel + (dt * aNet_y);
        this.xxPos = this.xxPos + (dt * this.xxVel);
        this.yyPos = this.yyPos + (dt * this.yyVel);
    }
    public void draw(){
        StdDraw.picture(xxPos ,yyPos , imgFileName);
    }
}
