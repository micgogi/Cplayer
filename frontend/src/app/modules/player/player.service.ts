import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Player } from './player';
import { retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {


  newPlayer:Player;
  //tmdb_endpoint: string;
 // imagePrefix: string;
  apiKey: string;
  watchlistEndpoint: string;
  searchEndipoint: string;
  playerId: any;

  constructor(private http: HttpClient) {
    this.apiKey = "xy6Tw1kIsBWc2VJyUMPE41KfKqK2";
 

    this.watchlistEndpoint = "http://localhost:8092/api/v1/playerservice/player";
  }

  
  searchPlayer(searchKey: string): Observable<any>{
    let searchUrl : string =  "https://cricapi.com/api/playerFinder?apikey="+this.apiKey+"&name="+searchKey+"";
    return this.http.get(searchUrl);

  }

  addPlayerToFavlist(player) {
    
    return this.http.post(this.watchlistEndpoint, player);
  }

  getFavouritePlayers(): Observable<Array<any>> {
    return this.http.get<Array<any>>(this.watchlistEndpoint);
  }

  deletePlayerFromWatchlist(player) {
    const delUrl = `${this.watchlistEndpoint}/${player.id}`;
    return this.http.delete(delUrl, {responseType: 'text'});
  }

  showDetails(plid){
    console.log("service");
    console.log(plid);
    this.playerId=plid;
   
  }
  savepId(plid){
    this.playerId=plid;
  }

  showDetail(){
    
   const showUrl =  "https://cricapi.com/api/playerStats?apikey="+this.apiKey+"&pid="+this.playerId;
    return this.http.get(showUrl);
  }

  
}
