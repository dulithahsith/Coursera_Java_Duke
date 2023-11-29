public class Part1 {
	public static void main(String[] args){
		Part1 pr=new Part1();
		System.out.println((pr.findSimpleGene("AAATGCCCTAACTAGATTAAGAAACC")));
	}
	public String findSimpleGene(String dna){
		int i=dna.indexOf("ATG");
		if (i==-1){
			return null;}
		String x=dna.substring(i);
		int j=x.indexOf("TAA")+i;
		if (j-i==-1){
			return null;
			}
		int mul=(j-i+3)%3;
		if(mul==0){
			return dna.substring(i+3, j);
		}
		else{
			return null;
		}

	}
}
