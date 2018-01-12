import { Component } from '@angular/core';
import { NavController, NavParams, AlertController, ModalController } from 'ionic-angular';
import { NewPastPage } from '../new-past/new-past';
import { PastDetailPage } from '../past-detail/past-detail';
import { PastData } from '../../providers/past-data';

/*
  Generated class for the Pastquestions page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-pastquestions',
  templateUrl: 'pastquestions.html'
})
export class PastquestionsPage {

	newpastpage = NewPastPage;
  pastdetailpage = PastDetailPage;
  //editpastpage = EditPastPage;

  public pasts = [];

  constructor(public navCtrl: NavController, public navParams: NavParams, public alertCtrl: AlertController, public modalCtrl: ModalController, public dataService: PastData) {

  	this.dataService.getData().then((pasts) => {
 
      if(pasts){
        this.pasts = JSON.parse(pasts); 
      }
 
    });
  }

   addPast(){
 
    let addModal = this.modalCtrl.create(this.newpastpage)
 
    addModal.onDidDismiss((past) => {
 
          if(past){
            this.savePast(past);
          }
 
    });
 
    addModal.present();
 
  }
  //editPast(){
 
    //let addModal = this.modalCtrl.create(this.editpastpage)
 
    //addModal.onDidDismiss((past) => {
 
    //      if(past){
      //      this.saveEditPast(past);
    //      }
 
    //});
 
    //addModal.present();
 
  ////}

  savePast(past){
    this.pasts.push(past);
    this.dataService.save(this.pasts);
  }
  saveEditPast(past){
    this.pasts.push(past);
    this.dataService.save(this.pasts);
  }
 
  viewPast(past){
    this.navCtrl.push(PastDetailPage, {
    past: past
    });
  //console.log(past);
}

  ionViewDidLoad() {
    console.log('ionViewDidLoad PastquestionsPage');
  }

}
