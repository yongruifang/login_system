import { defineStore } from "pinia";

export const useActorStore = defineStore('actor', {
    state: () =>  ({
        actors: {},
    }),
    getters: {
        getActors: (state) => Array.prototype.slice.call(state.actors),
        getTenthActor: (state) => state.actors[9],
        getTenActors: (state) => Array.prototype.slice.call(state.actors, 10, 15)
    },
    actions: {
        setActors(actors) {
            this.actors = actors
        }
        ,
        modifyActor(actorId) {
            
        }
    }
})