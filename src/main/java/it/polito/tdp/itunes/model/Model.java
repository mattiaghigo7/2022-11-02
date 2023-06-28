package it.polito.tdp.itunes.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	
	private ItunesDAO dao;
	
	private List<Genre> allGenres;
	
	private Graph<Track, DefaultEdge> grafo;
	private List<Track> vertici;
	private Map<Integer,Track> vMap;
	private List<Coppia> archi;
	
	private List<Track> max;
	private List<Track> migliore;
	private int durata;
	
	public Model() {
		this.dao=new ItunesDAO();
		this.allGenres=new ArrayList<>(dao.getAllGenres());
	}
	
	public List<Track> calcolaPlaylist(int durata){
		this.max=new ArrayList<>();
		this.migliore=new ArrayList<>();
		this.durata=durata;
		int i = 0;
		Set<Track> maxSet = new HashSet<>();
		List<Set<Track>> connesse = this.calcolaConnessa();
		for(Set<Track> s : connesse) {
			if(s.size()>i) {
				maxSet=new HashSet<>(s);
				i=s.size();
			}
		}
		for(Track t : maxSet) {
			this.max.add(t);
		}
		List<Track> parziale = new ArrayList<>();
		ricorsione(parziale);
		return migliore;
	}
	
	private void ricorsione(List<Track> parziale) {
		if(parziale.size()>migliore.size()) {
			migliore=new ArrayList<>(parziale);
		}
		for(Track t : max) {
			if((calcolaParziale(parziale)+t.getMilliseconds())<=this.durata && !parziale.contains(t)) {
				parziale.add(t);
				ricorsione(parziale);
				parziale.remove(t);
			}
		}
	}

	private int calcolaParziale(List<Track> parziale) {
		int d = 0;
		for(Track t : parziale) {
			d+=t.getMilliseconds();
		}
		return d;
	}

	public void creaGrafo(Genre g, int min, int max) {
		this.grafo=new SimpleGraph<>(DefaultEdge.class);
		this.vertici=new ArrayList<>(dao.getVertici(g, min, max));
		this.vMap=new HashMap<>();
		for(Track t : this.vertici) {
			this.vMap.put(t.getTrackId(), t);
		}
		Graphs.addAllVertices(this.grafo, this.vertici);
//		System.out.println(grafo.vertexSet().size());
		
		this.archi=new ArrayList<>(dao.getArchi(g, min, max, vMap));
		for(Coppia c : this.archi) {
			Graphs.addEdgeWithVertices(this.grafo, c.getT1(), c.getT2());
		}
	}
	
	public List<Set<Track>> calcolaConnessa() {
		ConnectivityInspector<Track, DefaultEdge> inspector = new ConnectivityInspector<>(this.grafo);
		return inspector.connectedSets();
	}
	
//	public List<List<Track>> calcolaConnessa() {
//		List<List<Track>> connesse = new ArrayList<>();
//		List<Track> presenti = new ArrayList<>();
//		for(Track t : this.grafo.vertexSet()) {
//			if(!presenti.contains(t)) {
//				DepthFirstIterator<Track, DefaultEdge> iterator = new DepthFirstIterator<>(this.grafo,t);
//				List<Track> compConnessa = new ArrayList<>();
//				while(iterator.hasNext()) {
//					compConnessa.add(iterator.next());
//				}
//				connesse.add(compConnessa);
//				presenti.add(t);
//				presenti.addAll(compConnessa);
//			}
//		}
//		
//		return connesse;
//	}
	
	public List<Genre> getGenres(){
		this.allGenres.sort(null);
		return this.allGenres;
	}
	
	public ValoriAmmessi getValori(Genre g) {
		return dao.getValori(g.getGenreId());
	}

	public int getVSize() {
		return this.grafo.vertexSet().size();
	}

	public int getASize() {
		return this.grafo.edgeSet().size();
	}

	public int getP(Track track) {
		int p = 0;
		for(Coppia c : this.archi) {
			if(c.getT1().getTrackId()==track.getTrackId() || c.getT2().getTrackId()==track.getTrackId()) {
				p=c.getN();
			}
		}
		return p;
	}
	
}
