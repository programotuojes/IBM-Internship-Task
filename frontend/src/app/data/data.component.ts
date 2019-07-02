import { Component, OnInit } from '@angular/core';
import { Broadcast } from '../broadcast';
import { BroadcastService } from '../broadcast.service';

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.css']
})
export class DataComponent implements OnInit {

  broadcasts: Broadcast[];
  
  constructor(private broadcastService: BroadcastService) { }

  ngOnInit() {
    this.broadcastService.getBroadcasts().subscribe(data => {
      this.broadcasts = data;
    })
  }
}
