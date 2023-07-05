import {Application, Router} from "https://deno.land/x/oak@v11.1.0/mod.ts";
import {oakCors} from "https://deno.land/x/cors@v1.2.2/mod.ts";
import skills from "./skills.json" assert {type: "json"};

const router = new Router();
router
    .get("/api/skills", (context) => {
        context.response.body = skills;
    })

const app = new Application();
app.use(oakCors()); // Enable CORS for All Routes
app.use(router.routes());
app.use(router.allowedMethods());

await app.listen({port: 8000});