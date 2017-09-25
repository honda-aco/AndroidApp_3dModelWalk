package com.ns.aco.sp.renderer;

public class VboInfo {

	private String _vertexFilePath = null;
	private String _normalFilePath = null;
	private String _coodFilePath = null;
	private String _pngFilePath = null;
	private int _faceCount = 0;
	private int _buffNoVertex = 0;
	private int _buffNoNormal = 0;
	private int _buffNoCood = 0;
	private int _glTextureId = 0;

	public VboInfo(String vertexFilePath, String normalFilePath, String coodFilePath){
		_vertexFilePath = vertexFilePath;
		_normalFilePath = normalFilePath;
		_coodFilePath = coodFilePath;
	}

	public VboInfo(String vertexFilePath, String normalFilePath, String coodFilePath, String pngFilePath){
		_vertexFilePath = vertexFilePath;
		_normalFilePath = normalFilePath;
		_coodFilePath = coodFilePath;
		_pngFilePath = pngFilePath;
	}

	public void setFaceCount(Integer faceCount){
		_faceCount = faceCount;
	}

	public void setGlTextureId(Integer glTextureId){
		_glTextureId = glTextureId;
	}

	public int getFaceCount(){
		return _faceCount;
	}

	public void setBuffNoCood(Integer buffNoCood){
		_buffNoCood = buffNoCood;
	}

	public Integer getBuffNoCood(){
		return _buffNoCood;
	}

	public void setBuffNoNormal(Integer buffNoNormal){
		_buffNoNormal = buffNoNormal;
	}

	public Integer getBuffNoNormal(){
		return _buffNoNormal;
	}

	public void setBuffNoVertex(Integer buffNoVertex){
		_buffNoVertex = buffNoVertex;
	}

	public Integer getBuffNoVertex(){
		return _buffNoVertex;
	}

	public String getVertexFilePath(){
		return _vertexFilePath;
	}

	public String getNormalFilePath(){
		return _normalFilePath;
	}

	public String getCoodFilePath(){
		return _coodFilePath;
	}

	public String getPngFilePath(){
		return _pngFilePath;
	}

	public int getGlTextureId(){
		return _glTextureId;
	}
}
