package team060.core;
/** 
 * @author Zahra Hasan A Alhilal
 *  
 */
public class Living {
	
	private Boolean wifi;
	private Boolean television;
	private Boolean satellite;
	private Boolean streaming;
	private Boolean dvdPlayer;
	private Boolean boardGames;
	
	public Living(Boolean wifi, Boolean television,Boolean satellite,Boolean streaming,
			Boolean dvdPlayer,Boolean boardGames) {
		this.wifi = wifi;
		this.television = television;
		this.satellite = satellite;
		this.streaming = streaming;
		this.dvdPlayer = dvdPlayer;
		this.boardGames = boardGames;
	}
	
	public Boolean hasWifi() {
		return wifi;
	}
	public Boolean hasTelevision() {
		return television;
	}
	public Boolean hasSatellite() {
		return satellite;
	}
	public Boolean hasStreaming() {
		return streaming;
	}
	public Boolean hasDvdPlayer() {
		return dvdPlayer;
	}
	public Boolean hasBoardGames() {
		return boardGames;
	}
}
