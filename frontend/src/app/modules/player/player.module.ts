import { NgModule } from '@angular/core';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ContainerComponent } from './components/container/container.component';
import { PlayerRouterModule } from './player-router.router';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { SharedModule } from '../shared/shared.module';
import { TokenInterceptorService } from './token-interceptor.service';
import { SearchPlayerComponent } from './components/search-player/search-player.component';
import { PlayerContainerComponent} from './components/player-container/player-container.component'
import { PlayerService } from './player.service';

@NgModule({
  imports: [
    SharedModule,
    HttpClientModule,
    PlayerRouterModule
  ],
  declarations: [
    ThumbnailComponent,
    ContainerComponent,
    WatchlistComponent,
    PlayerContainerComponent,
    SearchPlayerComponent
  ],
  entryComponents: [
  ],
  exports: [
    PlayerRouterModule,
    ThumbnailComponent
  ],
  providers: [PlayerService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }
  ]})
export class PlayerModule { }
