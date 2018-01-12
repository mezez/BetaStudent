import { Component } from '@angular/core';
import { NavController, NavParams, AlertController, ModalController} from 'ionic-angular';
import { NewFavlinkPage } from '../new-favlink/new-favlink';
import { FavlinkDetailPage } from '../favlink-detail/favlink-detail';
import { EditFavlinkPage } from '../edit-favlink/edit-favlink';
import { Favlinksp } from '../../providers/favlinksp';

/*
  Generated class for the Favlinks page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-favlinks',
  templateUrl: 'favlinks.html'
})
export class FavlinksPage {

  newfavlinkpage = NewFavlinkPage;
  favlinkdetailpage = FavlinkDetailPage;
  editfavlinkpage = EditFavlinkPage;

  public favlinks = [];


  constructor(public navCtrl: NavController, public navParams: NavParams,  public alertCtrl: AlertController, public modalCtrl: ModalController, public favlinkService: Favlinksp) {
 
  }


    addFavlink(){
 
      this.navCtrl.push(this.newfavlinkpage)   
 
  }
  editFavlink(favlink){

 
    
    this.navCtrl.push(this.editfavlinkpage, {
    favlink: favlink
    });
 
 
   
 
  }

  saveFavlink(favlink){
    this.favlinkService.createFavlink(favlink);
  }
  updateFavlink(favlink){
     this.favlinkService.updateFavlink({
              _id: favlink._id,
              _rev: favlink._rev,
              title: favlink.title,
              description: favlink.description,
              url: favlink.url
              
            });
  }
 
  viewFavlink(favlink){
    this.navCtrl.push(FavlinkDetailPage, {
    favlink: favlink
    });
  //console.log(assignment);
}


    deleteFavlink(favlink){
 
         this.favlinkService.deleteFavlink(favlink);

         let alert = this.alertCtrl.create({
      title: 'Success',
      subTitle: 'Favlink Deleted.',
      buttons: ['OK']
    });
    alert.present();
      
      this.favlinkService.getFavlink().then((data) => {
      this.favlinks = data;
    });
        
    }

  //newassPage(){
    //this.navCtrl.push(this.newasspage);
  //}
  favlinkdetailPage(){
    this.navCtrl.push(this.favlinkdetailpage);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad FavlinksPage');
    this.favlinkService.getFavlink().then((data) => {
      this.favlinks = data;
    });
  }

}
