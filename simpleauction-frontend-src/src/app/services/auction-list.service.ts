import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AuctionItem } from '../models/auction-list.model';
import { BidLog } from '../models/auction-list.model';
import { Bid } from '../models/auction-list.model';

//This service class will enables fetching the data from api and use it anywhere in the application using dependency injection. 

@Injectable({
  providedIn: 'root'
})
export class AuctionListService {
  //getUrl is the Url mapping to send requests to the Spring Boot controller class
  private getUrl: any = "http://localhost:8080/api/simpleauction"

  constructor(private _httpClient: HttpClient) {}

  //"Auctions" in <Auctions> is an auction model container imported from "../models/auction-list.model.ts" used as a vehicle to  make post and get requests from Spring Boot
  //return list of auctions from SpringBoot "getUrl" path which is "/auctionItems" using "GetMapping"
  getAuctionItems(): Observable<AuctionItem[]>{
    return this._httpClient.get<AuctionItem[]>(this.getUrl + "/auctionItems").pipe(
      map(response => response)
    )
  }

   //save an auction by posting an auction object parameter to SpringBoot "getUrl" path which is "/auctionItems" using "PostMapping"
  saveAuctionItem(auctionItem: AuctionItem[]): Observable<AuctionItem[]>{  
    return this._httpClient.post<AuctionItem[]>(this.getUrl + "/auctionItems", auctionItem).pipe(
      map(response => response)
    );
  }

  //save a bid by posting a bid object parameter to SpringBoot "getUrl" path which is "/bids" using "PostMapping"
  newBid(bid: Bid[]): Observable<void>{  
    return this._httpClient.post<void>(this.getUrl + "/newbids", bid).pipe(
      map(response => response)
    );
  }

  //return an AuctionItem by passing a URL parameter to SpringBoot "getUrl" path which is "/auctionItems/{id}" using "GetMapping"
  getAuctionItem(id: number): Observable<AuctionItem> {  
    return this._httpClient.get<AuctionItem>(`${this.getUrl + "/auctionItems"}/${id}`).pipe(
      map(response => response)
    )
  }

   //delete an auction item by passing URL parameter from SpringBoot "getUrl" path which is "/auctionItems/{id}" using "DeleteMapping"
  deleteAuctionItem(id: number): Observable<any> {  
    return this._httpClient.delete(`${this.getUrl + "/auctionItems"}/${id}`, {responseType: 'text'});
  }

  getBidLogs(): Observable<BidLog[]>{
    return this._httpClient.get<BidLog[]>(this.getUrl + "/bidLogs").pipe(
      map(response => response)
    )
  }

  saveBidLog(bidLog: BidLog[]): Observable<BidLog[]>{  
    return this._httpClient.post<BidLog[]>(this.getUrl + "/bidLogs", bidLog).pipe(
      map(response => response)
    );
  }
}