import { Component, OnInit } from '@angular/core';
import { Player } from '../../player';
import { PlayerService } from '../../player.service';

@Component({
  selector: 'search-player',
  templateUrl: './search-player.component.html',
  styleUrls: ['./search-player.component.css']
})
export class SearchPlayerComponent implements OnInit {

  players: Array<any>;
  listSize:number;

  constructor(private service: PlayerService) { 
  }

  ngOnInit() {
  }

  onEnter(searchKey) {
    console.log(searchKey);
    this.service.searchPlayer(searchKey).subscribe(response=>{
      this.players=response.data;
    })
  }
}
