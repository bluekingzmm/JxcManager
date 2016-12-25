package model;
import java.util.HashSet;
import java.util.Set;
public class TbRkthMain implements java.io.Serializable {
	//入库退货主菜单
	
	private static final long serialVersionUID = 1L;

	private String rkthId;
	private String pzs;
	private String je;
	private String ysjl;
	private String gysname;
	private String rtdate;
	private String czy;
	private String jsr;
	private String jsfs;
//	@SuppressWarnings("rawtypes")
	//SuppressWarnings压制警告,即去除警告 
	//rawtypes是说传参时也要传递带泛型的参数 
	private Set<TbRkthDetail> tbRkthDetails = new HashSet<TbRkthDetail>(0);
	public TbRkthMain() {
	}
	public TbRkthMain(String rkthId, String pzs, String je, String ysjl,
			String gysname, String rtdate, String czy, String jsr, String jsfs) {
		this.rkthId=rkthId;
		this.pzs = pzs;
		this.je = je;
		this.ysjl = ysjl;
		this.gysname = gysname;
		this.rtdate = rtdate;
		this.czy = czy;
		this.jsr = jsr;
		this.jsfs = jsfs;
	}
	public String getRkthId() {
		return this.rkthId;
	}

	public void setRkthId(String rkthId) {
		this.rkthId = rkthId;
	}

	public String getPzs() {
		return this.pzs;
	}

	public void setPzs(String pzs) {
		this.pzs = pzs;
	}

	public String getJe() {
		return this.je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	public String getYsjl() {
		return this.ysjl;
	}

	public void setYsjl(String ysjl) {
		this.ysjl = ysjl;
	}

	public String getGysname() {
		return this.gysname;
	}

	public void setGysname(String gysname) {
		this.gysname = gysname;
	}

	public String getRtdate() {
		return this.rtdate;
	}

	public void setRtdate(String rtdate) {
		this.rtdate = rtdate;
	}

	public String getCzy() {
		return this.czy;
	}

	public void setCzy(String czy) {
		this.czy = czy;
	}

	public String getJsr() {
		return this.jsr;
	}

	public void setJsr(String jsr) {
		this.jsr = jsr;
	}

	public String getJsfs() {
		return this.jsfs;
	}

	public void setJsfs(String jsfs) {
		this.jsfs = jsfs;
	}

	public Set<TbRkthDetail> getTbRkthDetails() {
		return this.tbRkthDetails;
	}

	public void setTbRkthDetails(Set<TbRkthDetail> tbRkthDetails) {
		this.tbRkthDetails = tbRkthDetails;
	}
}