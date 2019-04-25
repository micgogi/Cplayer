import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { PlayerContainerComponent} from './components/player-container/player-container.component';
import { AuthGuardService } from 'src/app/auth-guard.service';
import { SearchPlayerComponent } from './components/search-player/search-player.component';

const playerRoutes: Routes = [
    {
        path: 'players',
        canActivate: [AuthGuardService],
        children: [
            {
                path: '',
                redirectTo: '/players',
                pathMatch: 'full'
            },
            {
                path: 'details',
                component: PlayerContainerComponent,
            },
            // {
            //     path: 'top_rated',
            //     component: TpdbContainerComponent,
            //     data: {
            //         movieType: 'top_rated'
            //     },
            // },
            {
                path: 'FavList',
                component: WatchlistComponent,
            },
            {
                path: 'search',
                component: SearchPlayerComponent,
            }
        ]
    }
]


@NgModule({
    imports: [
        RouterModule.forChild(playerRoutes)
    ],
    exports: [
        RouterModule
    ]
})

export class PlayerRouterModule { }