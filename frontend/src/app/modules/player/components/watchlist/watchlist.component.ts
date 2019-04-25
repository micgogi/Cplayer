import { Component, OnInit } from '@angular/core';
import { PlayerService } from '../../player.service';
import { Player } from '../../player';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'movie-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {
  
  players: Array<any>;
  useWatchlistApi: boolean = true;
  check:boolean= false;

  constructor(private service: PlayerService, private snackBar: MatSnackBar) {
    this.players = [];
   }

  ngOnInit() {
    if(this.players.length==0){
      this.check=true;
    }
    let message="Favorite lis is empty";
    this.service.getFavouritePlayers().subscribe(players => {
      console.log(players);
      this.players.push(...players);
    },
    (error) =>
    {
        this.snackBar.open(message, '', {
          duration: 1000
        });
      
    })
  }

}
