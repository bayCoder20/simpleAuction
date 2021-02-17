export interface AuctionItem{
    auctionItemId: number;
    item: Item[];
    reservePrice: number;
    currentBid: number;
    maxAutoBidAmount: number;
    bidderName: string;
}

export interface Bid{
    auctionItemId: number;
    maxAutoBidAmount: number;
    currentBid: number;
    bidderName: string;
}

export interface Item {
    itemId: number;
    description: string;
}

export interface BidLog {
    bidLogId: number;
    auctionItemId: number;
    bidAmount: number;
    maxAutoBidAmount: number;
    bidderName: string;
    comment: String;
    createdDate: Date;
}