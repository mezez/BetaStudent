import { Component } from '@angular/core';
import { NavController, NavParams, ModalController, AlertController, ViewController } from 'ionic-angular';
import { NewTimetablePage } from '../new-timetable/new-timetable';
import { TimetableDetailPage } from '../timetable-detail/timetable-detail';
import { EditTimetablePage } from '../edit-timetable/edit-timetable';
import { Timetablep } from '../../providers/timetablep';

/*
  Generated class for the Timetables page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-timetables',
  templateUrl: 'timetables.html'
})
export class TimetablesPage {

	 newtimetablepage = NewTimetablePage;
  timetabledetailpage = TimetableDetailPage;
  edittimetablepage = EditTimetablePage;

  public timetables = [];

  constructor(public navCtrl: NavController, public navParams: NavParams, public alertCtrl: AlertController, public modalCtrl: ModalController, public timetableService: Timetablep) {}

  addTimetable(){
 
    let addModal = this.modalCtrl.create(this.newtimetablepage)
 
    addModal.onDidDismiss((timetable) => {
 
          if(timetable){
            this.saveTimetable(timetable);
          }
 
    });
 
    addModal.present();
 
  }
  editTimetable(timetable){

 
    
    this.navCtrl.push(this.edittimetablepage, {
    timetable: timetable
    });
 
 
   
 
  }

  saveTimetable(timetable){
    this.timetableService.createTimetable(timetable);
  }
  updateTimetable(timetable){
     this.timetableService.updateTimetable({
              _id: timetable._id,
              _rev: timetable._rev,
              title: timetable.title,
              eightnine: timetable.eightnine,
              nineten: timetable. nineten,
              teneleven:timetable.teneleven,
              eleventwelve:timetable. eleventwelve,
              twelveone:timetable. twelveone,
              onetwo: timetable.onetwo,
              twothree:timetable.ldays,
              threefour:timetable.threefour,
              fourfive:timetable.fourfive
            });
  }
 
  viewTimetable(timetable){
    this.navCtrl.push(TimetableDetailPage, {
    timetable: timetable
    });
  //console.log(assignment);
}


    deleteTimetable(timetable){
 
         this.timetableService.deleteTimetable(timetable);

         let alert = this.alertCtrl.create({
      title: 'Success',
      subTitle: 'Timetable Deleted.',
      buttons: ['OK']
    });
    alert.present();
      
      this.timetableService.getTimetable().then((data) => {
      this.timetables = data;
    });
        
    }

  //newassPage(){
    //this.navCtrl.push(this.newasspage);
  //}
  timetabledetailPage(){
    this.navCtrl.push(this.timetabledetailpage);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad TimetablesPage');
    this.timetableService.getTimetable().then((data) => {
      this.timetables = data;
    });
  }

}
