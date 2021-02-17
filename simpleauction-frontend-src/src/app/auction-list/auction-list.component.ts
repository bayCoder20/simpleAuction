import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { AuctionItem, BidLog } from '../models/auction-list.model';
import { AuctionListService } from '../services/auction-list.service';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';


@Component({
  selector: 'app-auction-list',
  templateUrl: './auction-list.component.html',
  styleUrls: ['./auction-list.component.scss']
})
export class AuctionListComponent implements OnInit {

  //auctionItems: AuctionItem[] = [];
  auctionItemResponse: any;
  auctionItems: any;
  bidLogResponse: any;
  modalRef: BsModalRef;
  modalContent = "";
  toBidDescription: string;
  toAuctionItemId: number;
  toCurrentBid: number;
  toMaxAutoBidAmount: number;
  toBidderName: string;
  toReservePrice: string;

  @ViewChild('template', { static: false }) modalTemplate: TemplateRef<any>;

  constructor(
    private auctionListService: AuctionListService,
    private modalService: BsModalService
  ) {}   //Creates instance of AuctionListService class when this component is initialized


  ngOnInit(): void {  //Automatically called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //this._auctionListService.getAuctionItems().subscribe(
      //data => this.auctionItems = data   FOR FILTERING      
    //)
    this.getAuctionItems();
  }

  deleteAuctionItem(id: number){
    this.auctionListService.deleteAuctionItem(id).subscribe(
      data => {
        console.log('deleted response', data);
        this.auctionListService.getAuctionItems();
      }
    )
  }

  getAuctionItems(){
    this.auctionListService.getAuctionItems().subscribe((res) => {
      this.auctionItems = res as AuctionItem[];
    })
  }

  openBidWindow(selectedItem: any){
    console.log("bid window opened for "+ selectedItem.bidderName);
  }

  openBidModal(template: TemplateRef<any>, selectedItem: any) {
    this.toBidderName = selectedItem.bidderName;
    this.modalContent = "bid window opened for "+ this.toBidderName;
    this.toBidDescription = selectedItem.item[0].description;
    this.toAuctionItemId = selectedItem.auctionItemId;
    this.toCurrentBid = selectedItem.currentBid;
    this.toMaxAutoBidAmount = selectedItem.maxAutoBidAmount;
    this.toReservePrice = selectedItem.reservePrice;
    console.log("selectedItem...");
    console.log(selectedItem);    
    this.modalRef = this.modalService.show(template);    
  }

  onClickSubmit(data) {
    this.newBid(data);
  }

  newBid(data){
    var newBidAmount:number = data.currentBid;
    var newMaxAutoBidAmount:number = data.maxAutoBidAmount;
    console.log(+newBidAmount + +1 + +newMaxAutoBidAmount);
    var logData = {"auctionItemId":this.toAuctionItemId,"bidderName":data.bidderName,"currentBid":data.currentBid,"maxAutoBidAmount":data.maxAutoBidAmount};
    if(+newBidAmount < +newMaxAutoBidAmount){  //check to see if max bid amount is higher than bid amount
      if(+newBidAmount + 1 > +this.toCurrentBid){  //check to see if bid amount is higher than current bid amount
        if (+newMaxAutoBidAmount + 1 > +this.toMaxAutoBidAmount){ //check to see if max auto bid amount is higher than current max auto bid
          data.auctionItemId = this.toAuctionItemId;
          console.log("data...");
          console.log(data);
          this.auctionListService.newBid(data).subscribe(
            data => {
              console.log('new bid response', data);
              this.saveBidLog(logData);            
              this.getAuctionItems();
            }
          )
        }
        else {
          alert("You have been out bidded, please set a higher bid amount");
          //NEED TO INCREASE THE CURRENT BID TO data.maxAutoBidAmount
          //var itemJSONArray = [{"description":data.description}];
          //set currentBid equal to the out bidded maxAutoBidAmount + 1.  Alos need to compare new currentBid with currentMaxAutoBid.
          var newCurrentBid = +newMaxAutoBidAmount + +1;
          var itemJSONArray = [{"description":this.toBidDescription}];
          var setCurrentBidJSONObject = {"auctionItemId":this.toAuctionItemId,"currentBid":newCurrentBid,"maxAutoBidAmount":this.toMaxAutoBidAmount,"bidderName":this.toBidderName,"reservePrice":this.toReservePrice,"item":itemJSONArray,};  
          var setBidLogJSONObject = {"auctionItemId":this.toAuctionItemId,"bidderName":this.toBidderName,"currentBid":newCurrentBid,"maxAutoBidAmount":this.toMaxAutoBidAmount,"comment":"Auto bid engaged to " + (+newMaxAutoBidAmount + +1).toFixed(2)}; 
          this.saveAuctionItem(setCurrentBidJSONObject);
          this.saveBidLog(setBidLogJSONObject);    
          this.getAuctionItems();
        }
      }      
      else {
        alert("Current bid is $" + this.toCurrentBid + "\nBid amount must be at least $" + (+this.toCurrentBid + +1).toFixed(2));
      }
    }
    else {
      alert("Exception: Max auto bid amount must be greater than bid amount!");
    }
  }

  saveBidLog(logData){    
    this.auctionListService.saveBidLog(logData).subscribe((res) => {
      this.bidLogResponse = res as BidLog[];
    })
  }

  saveAuctionItem(data){
    this.auctionListService.saveAuctionItem(data).subscribe((res) => {
      this.auctionItemResponse = res as AuctionItem[];
      this.modalRef.hide();
      this.getAuctionItems();
    })
  }

}
