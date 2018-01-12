import { Component, ViewChild,  ViewContainerRef } from '@angular/core';
import { Nav, Platform } from 'ionic-angular';
import { StatusBar, Splashscreen, SQLite } from 'ionic-native';

import { WelcomePage } from '../pages/welcome/welcome';
import { CoursesPage } from '../pages/courses/courses';
import { ProjectsPage } from '../pages/projects/projects';
import { AssignmentsPage } from '../pages/assignments/assignments';
import { NewAssPage } from '../pages/new-ass/new-ass';
import { NewExamPage } from '../pages/new-exam/new-exam';
import { ExamDetailPage } from '../pages/exam-detail/exam-detail';
import { AssDetailPage } from '../pages/ass-detail/ass-detail';
import { ProjectDetailPage } from '../pages/project-detail/project-detail';
import { EditProjectPage } from '../pages/edit-project/edit-project';
import { EditAssPage } from '../pages/edit-ass/edit-ass';
import { EditExamPage } from '../pages/edit-exam/edit-exam';
import { EditTimetablePage } from '../pages/edit-timetable/edit-timetable';
import { NewProjectPage } from '../pages/new-project/new-project';
import { TimetablesPage } from '../pages/timetables/timetables';
import { ExamsPage } from '../pages/exam/exam';
import { PastquestionsPage } from '../pages/pastquestions/pastquestions';
import { NewPastPage } from '../pages/new-past/new-past';
import { PastDetailPage } from '../pages/past-detail/past-detail';
import { NewCoursePage } from '../pages/new-course/new-course';
import { CourseDetailPage } from '../pages/course-detail/course-detail';
import { EditCoursePage } from '../pages/edit-course/edit-course';
import { FavlinksPage } from '../pages/favlinks/favlinks';
import { NewFavlinkPage } from '../pages/new-favlink/new-favlink';
import { FavlinkDetailPage } from '../pages/favlink-detail/favlink-detail';
import { EditFavlinkPage } from '../pages/edit-favlink/edit-favlink';

import { Page1 } from '../pages/page1/page1';
import { Page2 } from '../pages/page2/page2';


@Component({
  templateUrl: 'app.html',
  providers: [],
})
export class MyApp {

  public database: SQLite;
  @ViewChild(Nav) nav: Nav;

  rootPage: any = WelcomePage;

  pages: Array<{title: string, component: any}>;

  constructor(public platform: Platform) {
    this.initializeApp();

    // used for an example of ngFor and navigation
    this.pages = [

      { title: 'Lecture Timetables', component: TimetablesPage },
      { title: 'Exam Timetables', component: ExamsPage },
      { title: 'Courses', component: CoursesPage },
      { title: 'Past Questions', component: PastquestionsPage },
      { title: 'Projects', component: ProjectsPage },
      { title: 'Assignments', component: AssignmentsPage },
      { title: 'Bookmarks', component: FavlinksPage },

      { title: 'Info', component: Page1 },

    ];

  }

  initializeApp() {
    this.platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      StatusBar.styleDefault();
      Splashscreen.hide();

    
    });
  }

  openPage(page) {
    // Reset the content nav to have just this page
    // we wouldn't want the back button to show in this scenario
    this.nav.setRoot(page.component);
  }


}
