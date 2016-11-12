package modbotsim;

public class Modbot {

	private static class Motor {

		double curr_velocity;

		public Motor() {
			curr_velocity = 0.;
		}

		public void set_velocity(double velocity) {
			curr_velocity = velocity;
		}

		public double get_velocity() {
			return curr_velocity;
		}
	}

	double pos_x;
	double pos_y;

	Motor motor1;
	Motor motor2;
	Motor motor3;
	Motor motor4;

	public Modbot(double x, double y) {
		pos_x = x;
		pos_y = y;
		motor1 = new Motor();
		motor2 = new Motor();
		motor3 = new Motor();
		motor4 = new Motor();
	}

	public double x() {
		return pos_x;
	}

	public double y() {
		return pos_y;
	}
}