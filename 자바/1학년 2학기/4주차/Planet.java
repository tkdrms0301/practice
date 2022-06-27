public enum Planet {
   	MERCURY(3.303e+23, 2.4397e6), VENUS(4.869e+24, 6.0518e6), EARTH(5.976e+24, 6.37814e6),
    	MARS(6.421e+23, 3.3972e6),	JUPITER(1.9e+27,   7.1492e7), SATURN(5.688e+26, 6.0268e7),
    	URANUS(8.686e+25, 2.5559e7), NEPTUNE(1.024e+26, 2.4746e7);

   	private final double ����, ������;

   	Planet(double m, double r) { ���� = m; ������ = r; }
   	//�Ӽ� getters 
   	private double mass()		{return ����;}
   	private double radius() 	{return ������;}

   	public static final double G = 6.6730E-11; 
   	double ǥ���߷°��ӵ�() { return G*����/(������*������); }
   	double ǥ�鹫��(double ����) { return ���� * ǥ���߷°��ӵ�(); }

   	public static void main(String[] args) {
	  double �������� = Double.parseDouble(args[0]);
	  double �������� = �������� / EARTH.ǥ���߷°��ӵ�();

	  for (Planet p : Planet.values()) 
		System.out.printf(" %-8s������ ����: %5.1f%n", p, �������� * p.ǥ���߷°��ӵ�());
  	}
}