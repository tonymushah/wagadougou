use actix_web::{get, Responder, HttpServer, App};

#[get("/")]
async fn hello_world() -> impl Responder{
    "Hello World"
}

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    HttpServer::new(|| {
        App::new()
            .service(hello_world)
    })
        .bind(("localhost", 8082))?
        .run()
        .await
}
