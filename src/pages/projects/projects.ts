import { Component } from '@angular/core';
import { NavController, NavParams, AlertController, ModalController } from 'ionic-angular';
import { NewProjectPage } from '../new-project/new-project';
import { ProjectDetailPage } from '../project-detail/project-detail';
import { EditProjectPage } from '../edit-project/edit-project';
import { ProjectData } from '../../providers/projectdata';


/*
  Generated class for the Projects page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-projects',
  templateUrl: 'projects.html'
})
export class ProjectsPage {
	newprojectpage = NewProjectPage;
  projectdetailpage = ProjectDetailPage;
  editprojectpage = EditProjectPage;

  public projects = [];

  constructor(public navCtrl: NavController, public navParams: NavParams, public alertCtrl: AlertController, public modalCtrl: ModalController, public projectService: ProjectData) {

 
  }

     addProject(){
 
    let addModal = this.modalCtrl.create(this.newprojectpage)
 
    addModal.onDidDismiss((project) => {
 
          if(project){
            this.saveProject(project);
          }
 
    });
 
    addModal.present();
 
  }

  editProject(project){

 
    //let editModal = this.modalCtrl.create(this.editprojectpage, project);
    this.navCtrl.push(this.editprojectpage, {
    project: project
    });

  }

  saveProject(project){
    this.projectService.createProject(project);
  }

   updateProject(project){
     this.projectService.updateProject({
              _id: project._id,
              _rev: project._rev,
              title: project.title,
              description: project.description,
              date: project.date
            });
  }
 
  viewProject(project){
    this.navCtrl.push(ProjectDetailPage, {
    project: project
    });

}



      deleteProject(project){
 
        this.projectService.deleteProject(project);

         let alert = this.alertCtrl.create({
      title: 'Success',
      subTitle: 'Project Deleted.',
      buttons: ['OK']
    });
    alert.present();
      
      this.projectService.getProject().then((data) => {
      this.projects = data;
    });
        
    }

  newprojectPage(){
  	this.navCtrl.push(this.newprojectpage);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad ProjectsPage');
    this.projectService.getProject().then((data) => {
      this.projects = data;
    });
  }

}
