package cx.myhome.u1lowisland.komugipvp.library;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import javax.vecmath.AxisAngle4d;
import javax.vecmath.Matrix3d;
import javax.vecmath.Quat4d;
import java.util.ArrayList;

public class DrawForm {

    public ArrayList<Vector> drawCircleLocations(double radius, int amount){

        ArrayList<Vector> vecs = new ArrayList<>();
        final double MAXDEGREE = 360;

        for (double degree = 0; degree < MAXDEGREE; degree++) {

            double rad = Math.toRadians(degree);

            double x = radius * Math.cos(rad);
            double z = radius * Math.sin(rad);

            Vector vec = new Vector(x,0,z);

            if(degree % (360/amount)==0) {
                vecs.add(vec);
            }
        }

        return vecs;
    }

    public ArrayList<Vector> drawLineLocations(Vector start, Vector end, int amount) {

        ArrayList<Vector> vecs = new ArrayList<>();

        for(double line = 0; line < amount; line++){

            double x = start.getX()+((end.getX()-start.getX())*(line/amount));

            double z = start.getZ();

            if(end.getX() == start.getX()){
                z += ((end.getZ()-start.getZ())*(line/amount));
            }else {
                z = ((end.getZ() - start.getZ()) / (end.getX() - start.getX())) * (x - start.getX()) + start.getZ();
            }

            Vector vec = new Vector(x,0,z);

            vecs.add(vec);
        }
        return vecs;
    }

    public Quat4d loquat(Vector particleVec, Location playerLook, double dist) {

        double pitch = playerLook.getPitch()+90;
        double yaw = playerLook.getYaw()-90;

        double x = particleVec.getX();
        double y = particleVec.getY();
        double z = particleVec.getZ();

        double size = Math.pow(x, 2) + Math.pow(y + dist, 2) +Math.pow(z, 2);
        size = Math.pow(size, 0.5);

        Quat4d a = new Quat4d(x, y+dist, z, 0);

        double axis_x = -Math.sin(Math.toRadians(yaw));
        double axis_z = Math.cos(Math.toRadians(yaw));

        double s = Math.sin(Math.toRadians(pitch / 2));
        double c = Math.cos(Math.toRadians(pitch / 2));

        Quat4d q = new Quat4d(s * axis_x, 0, s * axis_z,c);
        Quat4d q_con = new Quat4d(q);
        q_con.conjugate();

        q.mul(a);
        q.mul(q_con);
        q.scale(size);

        q.setX(q.getX());
        q.setY(q.getY());
        q.setZ(q.getZ());

        return q;

    }

}
