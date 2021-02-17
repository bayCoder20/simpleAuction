import { Component, OnInit, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef, ModalOptions } from 'ngx-bootstrap/modal';
import { AuctionListService } from '../services/auction-list.service';
import { AuctionItem, BidLog } from '../models/auction-list.model';
import { Router } from '@angular/router';
import {AuctionListComponent} from '../auction-list/auction-list.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  auctionItemResponse: any;
  modalRef: BsModalRef;
  modalOptions: ModalOptions;
  modalContent = "";
  bidLogs: any;
  config: any;
  auctionItems: any;
  //autionListComponent: AuctionListComponent;


  constructor(
    private modalService: BsModalService,
    private auctionListService: AuctionListService,
    private router: Router,
    private autionListComponent: AuctionListComponent
  ) { }

  ngOnInit(): void {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  openNewAuctionModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);    
  }

  openBidLogModal(template: TemplateRef<any>) {
    this.getBidLogItems();
    config: this.modalOptions = { class: 'modal-lg' };
    this.modalRef = this.modalService.show(template, { class: 'modal-lg' });       
  }

  getBidLogItems(){
    this.auctionListService.getBidLogs().subscribe((res) => {
      this.bidLogs = res as BidLog[];
      console.log(this.bidLogs);
    })
  }

  onClickSubmitAuctionItem(data) {
    //console.log(data);
    var itemJSONArray = [{"description":data.description}];
    var auctionJSONObject = {"item":itemJSONArray,"reservePrice":data.reservePrice};
    console.log(auctionJSONObject);
    this.saveAuctionItem(auctionJSONObject);
  }

  saveAuctionItem(data){
    this.auctionListService.saveAuctionItem(data).subscribe((res) => {
      this.auctionItemResponse = res as AuctionItem[];
      this.modalRef.hide();
      this.auctionListService.getAuctionItems();
    })
  }

  getAuctionItems(){
    // save current route first
    const currentRoute = this.router.url;

    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
        this.router.navigate([currentRoute]); // navigate to same route
    }); 
  }

}
