import { Component, OnInit } from '@angular/core';
import { PlayerService } from '../../player.service';
import { Player } from '../../player';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'player-tpdb-container',
  templateUrl: './player-container.component.html',
  styleUrls: ['./player-container.component.css']
})
export class PlayerContainerComponent implements OnInit {

  players: Array<Player>;
  playerType: string;
  playerDetail: any;
  matchType: any;

  constructor(private service: PlayerService, private route: ActivatedRoute) {
    this.players = [];
    this.route.data.subscribe((data) => {
      this.playerType = data.playerType;
      console.log(data);
      this.matchType=this.playerDetail.data.bowling.type.ODIs;
    });
  }

  ngOnInit() {
    this.service.playerId;
    this.service.showDetail().subscribe((data)=>{
      console.log(data);
      this.playerDetail=data;
   
    console.log(this.playerDetail);
    
    })
  }

}
