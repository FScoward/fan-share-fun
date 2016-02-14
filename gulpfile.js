var gulp = require("gulp");
var typescript = require('gulp-typescript');


gulp.task('tsc', function(){
  gulp.src('./resources/ts/*.ts')
    .pipe(typescript('tsconfig.json'))
    .js
    .pipe(gulp.dest('./public/javascripts'))

  gulp.src('./resources/ts/**/*.ts')
    .pipe(typescript('tsconfig.json'))
    .js
    .pipe(gulp.dest('./public/javascripts/service'))
})

gulp.task('watch', function() {
  gulp.watch('./resources/ts/*.ts', ['tsc'])
  gulp.watch('./resources/ts/**/*.ts', ['tsc'])
})

gulp.task('copy', function() {
  gulp.src('./node_modules/systemjs/dist/system-polyfills.js')
    .pipe(gulp.dest('./public/javascripts'))

  gulp.src('./node_modules/es6-shim/es6-shim.min.js')
    .pipe(gulp.dest('./public/javascripts'))

  gulp.src('./node_modules/angular2/bundles/angular2-polyfills.js')
    .pipe(gulp.dest('./public/javascripts'))

  gulp.src('./node_modules/systemjs/dist/system.src.js')
    .pipe(gulp.dest('./public/javascripts'))

  gulp.src('./node_modules/systemjs/dist/system.src.js')
    .pipe(gulp.dest('./public/javascripts'))

  gulp.src('./node_modules/rxjs/bundles/Rx.js')
    .pipe(gulp.dest('./public/javascripts'))

  gulp.src('./node_modules/angular2/bundles/angular2.dev.js')
    .pipe(gulp.dest('./public/javascripts'))

  gulp.src('./node_modules/angular2/bundles/http.dev.js')
    .pipe(gulp.dest('./public/javascripts'))


  copySemantic()

})

function copySemantic() {
  // copy javascript
  gulp.src('./semantic/dist/*.js').pipe(gulp.dest('public/javascripts'))

  // copy css
  gulp.src('./semantic/dist/*.css').pipe(gulp.dest('public/stylesheets'))

  gulp.src('./semantic/dist/themes/**').pipe(gulp.dest('public/stylesheets/themes'))
}


gulp.task('default', ['copy', 'tsc', 'watch']);