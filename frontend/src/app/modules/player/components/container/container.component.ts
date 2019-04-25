import { Component, OnInit, Input } from '@angular/core';
import { PlayerService } from '../../player.service';
import { Player } from '../../player';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'player-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {

  @Input()
  players: Array<any>;
  
  @Input()
  useWatchlistApi: boolean;

  constructor(private service: PlayerService, private snackBar: MatSnackBar, private router: Router) { }

  ngOnInit() {

  }

  addToWatchlist(player) {
    console.log("in componant");
    console.log(player);
    let message = `${player.name} added to Favoritelist`;
    this.service.addPlayerToFavlist(player).subscribe((player) => {
      this.snackBar.open(message, '', {
        duration: 1000
      });
    },
    (error)=>{
      this.snackBar.open("Already Existed", '', {
        duration: 1000
      });

    }
    )
  }

  deletePlayerFromWatchlist(player) {
    this.service.deletePlayerFromWatchlist(player).subscribe((result) => {
      let message = `${player.name} ${result}`;
      this.snackBar.open("Player Deleted", '', {
        duration: 1000
      });
      const index = this.players.indexOf(player);
      this.players.splice(index, 1);
    });
    
  }


}
