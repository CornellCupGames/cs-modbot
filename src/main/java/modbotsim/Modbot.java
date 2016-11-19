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

	public void set_velocity(int motorid, double velocity) {
		switch (motorid) {
			case 1: 
				motor1.set_velocity(velocity);
				break;
			case 2:
				motor2.set_velocity(velocity);
				break;
			case 3:
				motor3.set_velocity(velocity);
				break;
			case 4:
				motor4.set_velocity(velocity);
				break;
			default:
				break;
		}
	}
}