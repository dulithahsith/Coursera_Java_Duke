
public class Part2 {
	public static void main(String[] args) {
		Part2 pr=new Part2();
		int t=pr.findGene("AAATGCCCTAACTAGATTAAGAAACC");
		System.out.println((pr.findstopCodon("AAATGCCCTAACTAGATTAAGAAACC",t,"TAA")));
		
		//System.out.println(pr.mystery("RANTTTTAULA"));
	}
	public int findstopCodon(String dna,int i, String stopCodon){
		int l=i;
		int t=dna.indexOf(stopCodon,l);
		int n=0;
		while(t!=-1){
			l=t;
			if((t-i)%3==0){
				n=t;
				break;
			}
			t=dna.indexOf(stopCodon,l+1);
		}
		return n;
	}
	public int findGene(String dna){
		return dna.indexOf("ATG");
	
	}
	


}