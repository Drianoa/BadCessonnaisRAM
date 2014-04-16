package mockup.model;

import java.util.Random;

public enum Classement {
	NC,
	T5,
	T10,
	T20,
	T50,
	A1,
	A2,
	A3,
	A4,
	B1,
	B2,
	B3,
	B4,
	C1,
	C2,
	C3,
	C4,
	D1,
	D2,
	D3,
	D4;

	public static Classement getRandomClassement() {
		Random random = new Random();
		int numeroDesDieux = random.nextInt(Classement.values().length);
		return Classement.values()[numeroDesDieux];
	}
}
