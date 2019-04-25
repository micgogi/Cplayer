import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { Player } from '../../player';
import { MatDialog } from '@angular/material/dialog';
import { PlayerService } from '../../player.service';
import { Router } from '@angular/router';


@Component({
  selector: 'player-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {

  @Input()
  player: Player;
  
  @Input()
  useWatchlistApi: boolean;

  @Output()
  addPlayer = new EventEmitter();

  @Output()
  deletePlayer = new EventEmitter();

  @Output()
  showDetails = new EventEmitter();

  constructor(private dialog: MatDialog, private service: PlayerService,private router:Router) { }

  ngOnInit() { }

  addToWatchlist() {
    console.log("thumbnail");
    this.addPlayer.emit(this.player);
  }

  deleteFromWatchlist() {
    this.deletePlayer.emit(this.player);
  }

  Details1(){
    this.showDetails.emit(this.player)
  }

  Details(plid){
  console.log(plid)  ;
  this.service.showDetails(plid)
  this.router.navigate(['/players/details']);

}

}
