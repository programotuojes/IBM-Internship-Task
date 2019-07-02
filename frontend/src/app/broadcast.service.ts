import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Broadcast } from './broadcast';

@Injectable({
  providedIn: 'root'
})
export class BroadcastService {

  private broadcastsUrl: string;

  constructor(private http: HttpClient) {
    this.broadcastsUrl = 'http://localhost:8080/live';
  }

  public getBroadcasts(): Observable<Broadcast[]> {
    return this.http.get<Broadcast[]>(this.broadcastsUrl);
  }
}
